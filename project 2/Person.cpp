#include "Person.h"
#include "Florist.h"
#include "FlowersBouquet.h"

Person::Person(std::string name) : name(name)
{
}

std::string Person::getName()
{
    return name;
}

void Person::orderFlowers(Florist* florist, Person* person, std::vector<std::string> order)
{
    std::cout << getName() << " orders flowers to " << person->getName() << " from " << florist->getName() << ": " << VectorToString(order) << std::endl;
    florist->acceptOrder(person, order);
}

void Person::acceptFlowers(FlowersBouquet*  bouquet)
{
    std::cout << getName() << " accepts the flowers: " << VectorToString(bouquet->getBouquet()) << std::endl;
}

std::string Person::VectorToString(std::vector<std::string> vec)
{
    std::string result;

    for (auto elem : vec)
    {
        result= result + elem + ", ";
    }
    result = result.substr(0, result.length()-2)+".";
    return result;
}