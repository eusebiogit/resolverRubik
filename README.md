# resolverRubik
Programa para resolver un cubo de rubik mediante estrategias de búsqueda en java

De momento la información del cubo se introduce cambiando el archivo cubo/cubo.txt:

Se podrán elegir entre varias estrategias de búsqueda, aún mediante código, desde la clase interfaz de usuario.

La solución presentada se compone del conjunto de acciones que se deben hacer sobre las coordenadas del cubo.

Por ejemplo:

    X0 -> Y2 -> X0 -> Y0 -> Y1 -> Z2 -> Z2 

Para llevar acabo esta operación el cubo físico debe situarse con la cara roja de frente al usuario y
La cara blanca a la derecha.
(El color de una cara lo indica la casilla central de la cara.)
n€(0,1,2)
Realizar Xn consiste en mover una fila de la cara roja, hacia la derecha. 
    

        Cara3:                                                  Cara 0:
            ------------------------------------                   ------------------------------------
            verde     || rojo      || naranja  |                   blanco    || amarillo  || amarillo |
            blanco    || amarillo  || naranja  |                   blanco    || rojo      || rojo     |
            naranja   || naranja   || amarillo |                   rojo      || rojo      || rojo     |
            ------------------------------------                   ------------------------------------

   cubo.X(0):


        Cara 0:
            ------------------------------------           Cara1:
            verde     || rojo      || naranja  |           -----------------------------------
            blanco    || rojo      || rojo     |           blanco    || amarillo  || amarillo |
            rojo      || rojo      || rojo     |           blanco    || blanco    || naranja  |
            ------------------------------------           blanco    || blanco    || naranja  |


Yn Consiste en mover una columna hacia abajo:

            Cara 0:
            ------------------------------------
            blanco    || amarillo  || amarillo |
            blanco    || rojo      || rojo     |
            rojo      || rojo      || rojo     |
            ------------------------------------
            
            cubo.Y(2);


            Cara5:
            ------------------------------------
            azul      || azul      || amarillo |
            amarillo  || azul      || rojo     |
            blanco    || verde     || rojo     |
            ------------------------------------


Zn Transpone una cara a partir de la cara roja, en profundidad hacia la derecha, z0 transpone la cara roja hacia la derecha, z2 la cara naranja en sentido opuesto.

            cubo.Z(2);

            Cara2:
            ------------------------------------
            blanco    || verde     || verde    |
            amarillo  || naranja   || naranja  |
            verde     || azul      || amarillo |
            ------------------------------------

            Cara2:
            ------------------------------------
            verde     || amarillo  || blanco   |
            azul      || naranja   || verde    |
            amarillo  || naranja   || verde    |




El objetivo del programa es a partir de un estado inicial del cubo, 

comprobará si es un estado solucion, 

    si es solución devuelve un String con los pasos hasta la solución.

    si no lo es generará los 9 movimientos posibles y los almacenará en una
    estructura de datos (frontera) en función de la estrategia de búsqueda seleccionada.

Repite hasta que sea solución o se vacíe la frontera

Para probar el programa:

    cd dist/

    java -jar resolverRubik.jar ruta_al_cubo.txt
    


