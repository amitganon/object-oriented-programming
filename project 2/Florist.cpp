#include "Florist.h"

Florist::Florist(std::string name, Wholesaler* w, FlowerArranger* f, DeliveryPerson* d) : Person(name)
{
    wholesaler = w;
    flowerArranger = f;
    deliveryPerson = d;

}
void Florist::acceptOrder(Person* p, std::vector<std::string>v)
{
    std::cout <<getName() << " forwards request to " << wholesaler->getName() << "." << std::endl;
    FlowersBouquet* flowersBouquet = wholesaler->acceptOrder(v);
    std::cout << wholesaler->getName() << " returns flowers to " << getName() << "." << std::endl;
    std::cout << getName() << " request flowers arrangement from " << flowerArranger->getName() << "." << std::endl;
    flowerArranger->arrangeFlowers(flowersBouquet);
    std::cout << flowerArranger->getName() << " returns arranged flowers to " << getName() << "." << std::endl;    
    std::cout << getName() << " forwards flowers to " << deliveryPerson->getName() << "." << std::endl;
    deliveryPerson->deliver(p, flowersBouquet);
}

std::string Florist::getName()
{
    return "Florist "+ Person::getName();
}
