#pragma once
#include "FlowersBouquet.h"
#include "Person.h"

class Gardner : public Person
{
public:
	Gardner(std::string);
	FlowersBouquet* prepareBouquet(std::vector<std::string>);
	std::string getName();
};

