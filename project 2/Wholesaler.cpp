#include "Wholesaler.h"

Wholesaler::Wholesaler(std::string name, Grower* g) : Person(name) {
    grower = g;
}
FlowersBouquet* Wholesaler::acceptOrder(std::vector<std::string> v)
{
    std::cout << getName() << " forwards the request to " << grower->getName() << "." << std::endl;
    FlowersBouquet* b = grower->prepareOrder(v);
    std::cout << grower->getName() << " returns flowers to " << getName() << "." << std::endl;
    return b;
}

std::string Wholesaler::getName()
{
    return "Wholesaler " + Person::getName();
}

