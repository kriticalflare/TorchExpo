"""GoogleNet"""
import torch
import torchvision


# load googlenet model
model = torchvision.models.googlenet(pretrained=True)
model.eval()
# example tensor as input
example = torch.rand(1, 3, 224, 224)
# create torchscript serialized pytorch pretrained model
traced_script_module = torch.jit.trace(model, example)
traced_script_module.save("../outputs/googlenet.pt")
