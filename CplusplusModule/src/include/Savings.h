//
// Created by gorka on 6/03/17.
//

#ifndef CPLUSPLUSMODULE_SAVINGS_H
#define CPLUSPLUSMODULE_SAVINGS_H

#include <stdio.h>
#include <vector>
#include <string>

class Savings {
private:
    int salary = 0;
    std::vector<std::string> * concepts;


public:
    Savings();
    int getSalary();

};


#endif //CPLUSPLUSMODULE_SAVINGS_H
