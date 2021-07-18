// HW2.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include "Person.h"
#include "FlowersBouquet.h"
#include "Wholesaler.h"
#include "FlowerArranger.h"
#include "DeliveryPerson.h"
#include "Gardner.h"
#include "Florist.h"

int main()
{
    Person* chris = new Person("Chris");
    Person* robin = new Person("Robin");
    DeliveryPerson* dylan = new DeliveryPerson("Dylan");
    FlowerArranger* flora = new FlowerArranger("Flora");
    Gardner* garent = new Gardner("Garett");
    Grower* gray = new Grower("Gray", garent);
    Wholesaler* watson = new Wholesaler("Watson", gray);
    Florist* fred = new Florist("Fred", watson, flora, dylan);
    
    std::vector<std::string> order = { "Roses", "Violets", "Gladiolus" };

    chris->orderFlowers(fred, robin, order);

    delete chris;
    delete robin;
    delete dylan;
    delete flora;
    delete garent;
    delete gray;
    delete watson;
    delete fred;
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
