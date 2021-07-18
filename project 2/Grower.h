#pragma once
#include "FlowersBouquet.h"
#include "Person.h"
#include "Gardner.h"

class Grower : public Person
{
private:
	Gardner* gardner;
public:
	Grower(std::string, Gardner*);
	FlowersBouquet* prepareOrder(std::vector<std::string>);
	std::string getName();
};

