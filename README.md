# ISAMoviles-MISW4203-2024-12

## Configuración del Proyecto

1. Clona este repositorio en tu máquina local utilizando el siguiente comando:
   ```
   git clone https://github.com/dparejaUniandes/ISAMoviles-MISW4203-2024-12.git
   ```
2. Abre el proyecto en Android Studio.

3. Espera a que el proyecto se sincronice y se descarguen las dependencias necesarias.

4. Puedes visualizar la aplicación en un emulador siguiendo los pasos descritos en la sección: [Ejecuta tu app en un dispositivo virtual (emulador)](https://developer.android.com/codelabs/basic-android-kotlin-training-first-template-project?hl=es-419#2) 

### Backend

El backend de la aplicación se encuentra desplegado en un servidor en la nube. Por lo tanto, no es necesario realizar alguna configuración adicional para ejecutar la aplicación.

## APK de la Aplicación

A continuación se encuentra el APK de la aplicación, el cual podrás descargar haciendo clic en este enlace:

* [app-vinilos-apk.zip](https://github.com/dparejaUniandes/ISAMoviles-MISW4203-2024-12/files/15288258/app-vinilos-apk.zip)

Puedes importarlo en Android Studio siguiendo los pasos descritos en: [Instalar o adicionar archivos en el emulador](https://developer.android.com/studio/run/emulator-install-add-files)

# Pruebas Automatizadas con Espresso para la Aplicación Vinilos

Este proyecto contiene pruebas automatizadas desarrolladas con Espresso para la aplicación Vinilos. Estas pruebas tienen como objetivo verificar el correcto funcionamiento de las funcionalidades principales de la aplicación.

## Requisitos

Antes de ejecutar las pruebas, asegúrate de tener lo siguiente:

- Android Studio instalado en tu sistema.
- Un emulador de Android configurado o un dispositivo físico conectado.
- La aplicación Vinilos instalada en el emulador o dispositivo.

## Ejecución de las Pruebas

Para ejecutar las pruebas automatizadas con Espresso, sigue estos pasos:

1. Abre la clase `VinilosAppTest` ubicada en el directorio `src/androidTest/java/com/example/vinilosapp/`.

2. Haz clic derecho en la clase `VinilosAppTest` y selecciona "Run 'VinilosAppTest'".

3. Android Studio iniciará el emulador o se conectará al dispositivo físico y ejecutará las pruebas automatizadas.

4. Podrás ver el progreso de las pruebas en la ventana "Run" de Android Studio.

5. Una vez finalizadas las pruebas, se mostrará un resumen de los resultados indicando si las pruebas pasaron o fallaron.

## Descripción de las Pruebas

Las pruebas automatizadas con Espresso para la aplicación Vinilos cubren las siguientes funcionalidades:

### HU01: Listado de Álbumes

- Se verifica que el listado de álbumes se muestre correctamente al usuario.
- Se comprueba que el primer elemento del listado contenga el nombre y el cover del álbum, como información basica de un album.
- Se simula la acción del usuario al hacer clic en un álbum para navegar a la pantalla de detalles.

### HU02: Detalles del Álbum

- Se asegura que la navegación desde la lista de álbumes hasta la pantalla de detalles funcione correctamente.
- Se verifica que la información detallada del álbum seleccionado se muestre de manera adecuada.
- Se comprueba que el botón "back" esté disponible para que el usuario pueda regresar a la lista de álbumes.
- Se simula la acción del usuario al hacer clic en el botón "back" y se verifica que se regrese correctamente a la lista de álbumes.
