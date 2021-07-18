#pragma once
#include "FlowersBouquet.h"
#include "Person.h"

class FlowerArranger : public Person
{
public :
	FlowerArranger(std::string);
	void arrangeFlowers(FlowersBouquet*);
	std::string getName();
};

