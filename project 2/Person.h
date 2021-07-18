#pragma once
#include "FlowersBouquet.h"

class Florist;// forward declaration
class DeliveryPerson;
class FlowerArranger;
class Gardner;
class Grower;
class Wholesaler;
class Person
{
protected:
	std::string name;
public:
	Person(std::string);
	virtual std::string getName();
	void orderFlowers(Florist*, Person*, std::vector<std::string>);
	void acceptFlowers(FlowersBouquet*);
	std::string VectorToString(std::vector<std::string> vec);

};