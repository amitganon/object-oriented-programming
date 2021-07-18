#include "Grower.h"

Grower::Grower(std::string name, Gardner* g) : Person(name)
{
    gardner = g;
}

FlowersBouquet* Grower::prepareOrder(std::vector<std::string> v)
{
    std::cout << getName() << " forwards the request to " << gardner->getName() << "." << std::endl;
    FlowersBouquet* b = gardner->prepareBouquet(v);
    std::cout << gardner->getName() << " returns flowers to " << getName() << "." <<std::endl;
    return b;
}

std::string Grower::getName()
{
    return "Grower " + Person::getName();
}
