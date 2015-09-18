Topologies can be viewed as layers.

In a topology you can :
- add nodes
- mask nodes
- substitute nodes

Each layer can be modified without modifying the others layers.

```
   _A___
 + ___B_
 
 = _A_B_
```
 
Here, m is a mask for A :
 
```
   _A___
 + _m_B_
 
 = ___B_
```
 
Here a is a substitution of A :

```
   _A___
 + _a_B_
 
 = _a_B_
``` 
 
This behavior could be added to properties, eventually to all maps (Custom Map) ...
 