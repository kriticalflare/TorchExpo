"""DeepLabV3-ResNet101"""
import torch
import torchvision


# NOTE: Currently this script works on PyTorch Nightly, as it supports custom dict outputs
# load deeplabv3-resnet101 model
model = torch.hub.load('pytorch/vision:v0.6.0', 'deeplabv3_resnet101', pretrained=True)
model.eval()
# example tensor as input
example = torch.rand(1, 3, 224, 224)
# create torchscript serialized pytorch pretrained model
traced_script_module = torch.jit.trace(model, example, strict=False)
traced_script_module.save("../outputs/deeplabv3.pt")
