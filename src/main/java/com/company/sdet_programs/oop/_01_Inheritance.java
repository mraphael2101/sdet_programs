package com.company.sdet_programs.oop;

// In Java, an interface can inherit from multiple interfaces
interface  _01_Inheritance extends Car, Plane {}

interface Car {}

interface Plane {}





// In Java, a class can ONLY inherit from one other class using the extends keyword
class _02_Inheritence extends House implements Mansion, SemiDetached {}

class House {}

class Flat {}

interface Mansion {}

interface SemiDetached {}