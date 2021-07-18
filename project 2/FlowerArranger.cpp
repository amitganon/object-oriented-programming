#include "FlowerArranger.h"

FlowerArranger::FlowerArranger(std::string name) : Person(name) {}
void FlowerArranger::arrangeFlowers(FlowersBouquet* f)
{
    f->arrange();
    std::cout << getName() << " arranges flowers."<< std::endl;
}

std::string FlowerArranger::getName()
{
    return "Flower Arranger "+ Person::getName();
}
