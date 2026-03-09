# Análisis del examen (2ª evaluación) - EnunciadoExameDomotic

## Nota previa
No aparece en el repositorio un documento de enunciado adicional (PDF/TXT/MD). El análisis se basa en el código entregado en `EnunciadoExameDomotic/src`.

## Qué está ya implementado
- Modelo base de dispositivo domótico con adaptador a driver en `DispositivoDomotico`.
- Driver genérico con comandos `ON/OFF` en `DriverDomoticoXenerico`.
- Menú de consola para crear dispositivos y lanzar comandos en `AplicacionDomotica`.
- Parseo básico de comandos con comillas en `CommandParser`.

## Qué pide claramente el examen en el código (pistas del propio enunciado embebido)
1. **Refactorización de duplicación en selección de opciones**
   - En `AplicacionDomotica` hay un comentario explícito: extraer método genérico `choose(...)` para evitar código duplicado entre `chooseDriver` y `chooseDeviceType`.

2. **Implementar la clase `Fogar`**
   - Están sin hacer (`TODO`) los métodos:
     - `addDevice(...)`
     - `getDevices()`
     - `getDevice(...)`
     - `getDriver(...)`

## Problemas detectados
- **Posible duplicado al añadir dispositivo**: en `deviceForm(...)` ya se hace `fogar.addDevice(device)` y en `main` (caso 1) se vuelve a llamar a `fogar.addDevice(d)`.
- **Inconsistencia de tipos ofrecidos vs fábrica**:
  - `getDeviceTypes()` ofrece `TV`, `PERSIANA`, `CLIMATIZADOR`, `LUZ`, `ENCHUFE`.
  - `DeviceFactory(...)` solo soporta `LUZ`.
  - Resultado: casi todos los tipos lanzan excepción de “non están soportados”.
- **Riesgo NPE en `toString()` de `DispositivoDomotico`**:
  - Usa `driver.getStatus()` sin comprobar `driver == null`.
  - Si se imprime un dispositivo sin driver asignado podría fallar.

## Lectura didáctica (nivel examen DAW)
- El ejercicio parece centrado en:
  - Uso de interfaces (`AdaptadorDomotico`, `DriverDomotico`).
  - Delegación de comportamiento en driver.
  - Estructuras `HashMap` para registrar drivers y dispositivos.
  - Refactorización simple para evitar repetición en menú.
  - Manejo básico de excepciones personalizadas (`DomoticException`).

## Orden recomendado para resolverlo
1. Completar `Fogar` (es bloqueante para funcionamiento básico).
2. Extraer método genérico `choose(...)`.
3. Corregir el doble `addDevice`.
4. Alinear tipos de dispositivo con `DeviceFactory` (o limitar menú a tipos soportados).
5. Añadir protección en `toString()` ante driver nulo.
