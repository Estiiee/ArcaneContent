package com.estie.arcanecontent.client.render.entity.layer;

import com.estie.arcanecontent.client.model.armor.ModelBloodedFireArmor;
import com.estie.arcanecontent.client.model.armor.ModelBloodedIceArmor;
import com.estie.arcanecontent.client.model.armor.ModelBloodedLightningArmor;
import com.estie.arcanecontent.common.item.blooded.BloodedDragonType;
import com.estie.arcanecontent.common.item.blooded.ItemBloodedArmor;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

public class LayerBloodedArmorOverlay<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    
    private static final int FRAME_COUNT = 8;
    private static final int TICKS_PER_FRAME = 4;
    
    private static final EquipmentSlot[] ARMOR_SLOTS = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    
    private final Map<BloodedDragonType.DragonElement, HumanoidModel<LivingEntity>[]> modelCache;
    
    @SuppressWarnings("unchecked")
    public LayerBloodedArmorOverlay(RenderLayerParent<T, M> renderer) {
        super(renderer);
        modelCache = new EnumMap<>(BloodedDragonType.DragonElement.class);
        for (BloodedDragonType.DragonElement element : BloodedDragonType.DragonElement.values()) {
            // index 0 = outer (CHEST/FEET), index 1 = inner (LEGS/HEAD)
            HumanoidModel<LivingEntity>[] pair = new HumanoidModel[2];
            pair[0] = (HumanoidModel<LivingEntity>) createElement(element, false);
            pair[1] = (HumanoidModel<LivingEntity>) createElement(element, true);
            modelCache.put(element, pair);
        }
    }
    
    private static HumanoidModel<?> createElement(BloodedDragonType.DragonElement element, boolean inner) {
        return switch (element) {
            case FIRE -> new ModelBloodedFireArmor(inner);
            case ICE -> new ModelBloodedIceArmor(inner);
            case LIGHTNING -> new ModelBloodedLightningArmor(inner);
        };
    }
    
    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight, @NotNull T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        
        M parentModel = this.getParentModel();
        
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack stack = entity.getItemBySlot(slot);
            if (!(stack.getItem() instanceof ItemBloodedArmor blooded)) continue;
            
            BloodedDragonType.DragonElement element = blooded.getDragonType().getElement();
            boolean inner = slot == EquipmentSlot.LEGS || slot == EquipmentSlot.HEAD;
            HumanoidModel<LivingEntity>[] pair = modelCache.get(element);
            if (pair == null) continue;
            
            HumanoidModel<LivingEntity> armorModel = inner ? pair[1] : pair[0];
            
            // 将状态（蹲伏/游泳等）从父模型同步过来
            // T 运行时就是 LivingEntity，强转安全
            @SuppressWarnings("unchecked") HumanoidModel<T> typedArmorModel = (HumanoidModel<T>) armorModel;
            parentModel.copyPropertiesTo(typedArmorModel);
            
            // 直接复制各骨骼旋转，与父模型完全同步，不受第三方动画 mod 影响
            armorModel.head.copyFrom(parentModel.head);
            armorModel.hat.copyFrom(parentModel.hat);
            armorModel.body.copyFrom(parentModel.body);
            armorModel.rightArm.copyFrom(parentModel.rightArm);
            armorModel.leftArm.copyFrom(parentModel.leftArm);
            armorModel.rightLeg.copyFrom(parentModel.rightLeg);
            armorModel.leftLeg.copyFrom(parentModel.leftLeg);
            
            setPartVisibility(armorModel, slot);
            
            ResourceLocation overlayTex = getOverlayTexture(blooded.getDragonType(), slot, entity);
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(overlayTex));
            armorModel.renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
        }
    }
    
    private ResourceLocation getOverlayTexture(BloodedDragonType type, EquipmentSlot slot, LivingEntity entity) {
        int frame = ((entity.tickCount / TICKS_PER_FRAME) % FRAME_COUNT) + 1;
        String prefix = type.getTexturePrefix();
        String suffix = slot == EquipmentSlot.LEGS ? "_armor_legs" + frame + ".png" : "_armor" + frame + ".png";
        return new ResourceLocation("iceandfire", "textures/models/armor/" + prefix + suffix);
    }
    
    private static void setPartVisibility(HumanoidModel<?> model, EquipmentSlot slot) {
        model.head.visible = slot == EquipmentSlot.HEAD;
        model.hat.visible = slot == EquipmentSlot.HEAD;
        model.body.visible = slot == EquipmentSlot.CHEST;
        model.leftArm.visible = slot == EquipmentSlot.CHEST;
        model.rightArm.visible = slot == EquipmentSlot.CHEST;
        model.leftLeg.visible = slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET;
        model.rightLeg.visible = slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET;
    }
}