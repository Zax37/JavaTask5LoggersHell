# Java w zastosowaniach produkcyjnych

## Zadanie 5 - LoggersHell

Zadanie polegające na wykorzystaniu dwóch gotowych modułów logujących informacje przy pomocy
różnych loggerów oraz przekierowanie tych informacji do Logbacka.

Budowanie:

- gradle build - buduje projekt razem z zależnościami

Przykładowe wywołanie:

- java -jar build/libs/JavaTask5LoggersHell-1.0-SNAPSHOT.jar -price 10 -age 22 -clientId 1 -companyId 1

W przypadku niepodania właściwych parametrów uruchomieniowych wyświetla się treść pomocy z ich opisem.
