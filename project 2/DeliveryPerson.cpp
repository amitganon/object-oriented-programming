#include "DeliveryPerson.h"


DeliveryPerson::DeliveryPerson(std::string name) : Person(name) {}
void DeliveryPerson::deliver(Person* p , FlowersBouquet* f)
{
    std::cout << getName() << " delivers flowers " << p->getName() << "." << std::endl;
    p->acceptFlowers(f);
}

std::string DeliveryPerson::getName()
{
    return "Delivery Person "+ Person::getName();
}
