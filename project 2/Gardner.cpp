#include "Gardner.h"

Gardner::Gardner(std::string name) : Person(name) {}
FlowersBouquet* Gardner::prepareBouquet(std::vector<std::string> v)
{
    FlowersBouquet* b = new FlowersBouquet(v);
    std::cout << getName() << " prepares flowers." << std::endl;
    return b;
}

std::string Gardner::getName()
{
    return "Gardener "+ Person::getName();
}
