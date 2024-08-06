[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/2PRHf7vk)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=15405224)
# Final LCIII 2023

## Introducción al problema:

Un cliente nos pide una aplicación que sea capaz de guardar los registros periodicos de los sensores de temperatura que posee en su fabrica. Para esto nos pide poder dar de alta los sensores informando el nombre del sensor, una descripción de la ubicación donde esta el sensor (por ejemplo en la entrada a la fabrica) y la unidad de medida en que el sensor mandará la información (grados C° o F°). Por otro lado, el sistema deberá proveer otra api para que los sensores manden las lecturas periodicas donde informarán su identificador, la fecha y hora de la lectura y la medida de su lectura.

Generar un proyecto en SpringBoot desde 0 usando la herramientas Maven, base de datos H2, Junit como framework para Test Unitarios y que cumpla con los siguientes requisitos.

 * CRUD de Sensor (GET all | GET by name | POST | PUT | DELETE)
 * CRUD de Lecturas (GET all | GET by dates (desde/hasta) de lectura | POST | PUT | DELETE)
Test Completos:
 * Api de filtrado por fecha (GET by dates) 
 * Alta de sensor (POST)
 * Incluir automatización de Swagger con la generación del archivo swagger.json
