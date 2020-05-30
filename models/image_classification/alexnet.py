"""AlexNet"""
import torch
import torchvision


# load alexnet model
model = torchvision.models.alexnet(pretrained=True)
model.eval()
# example tensor as input
example = torch.rand(1, 3, 224, 224)
# create torchscript serialized pytorch pretrained model
traced_script_module = torch.jit.trace(model, example)
traced_script_module.save("../outputs/alexnet.pt")
