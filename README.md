# Tema 1: Energex-Lite

Programul primește un fișier cu comenzi despre piața de energie. El salvează datele primite
din 15 în 15 minute, răspunde la cereri și scrie rezultatele într-un alt fișier.

## Cum este organizat

- `model`: datele propriu-zise (un interval de timp, o valoare măsurată, datele unei zile)
- `validation`: verifică dacă o valoare primită este corectă
- `repository`: locul unde sunt păstrate datele
- `command`: câte o clasă pentru fiecare comandă
- `response`: mesajele care ajung în fișierul de ieșire
- `engine`: ceasul simulării și clasa care pune totul în mișcare
- `fileio` și `main`: citirea fișierului și pornirea programului

Pe scurt, programul ia comenzile pe rând. Pentru fiecare, mută ceasul la ora comenzii, o
execută și, dacă e cazul, păstrează un răspuns. La final scrie toate răspunsurile.

## De ce am gândit așa

### Clase părinte și clase copil

Când mai multe clase aveau lucruri în comun, am pus partea comună într-o clasă părinte. Fiecare
clasă copil scrie doar ce are diferit.

- **Valorile din piață:** toate au un număr de interval, dar fiecare tip are alte câmpuri.
  Consumul real și cel prognozat arată la fel, așa că au un părinte comun, `GridPoint`.
- **Verificările:** unele se repetă, de exemplu „valoarea lipsește” sau „valoarea este
  negativă”. Sunt scrise o singură dată, în `PointValidator`. Dacă o valoare are mai multe
  probleme, este afișată doar prima.
- **Comenzile:** programul poate executa orice comandă fără să știe ce fel este. `import` și
  `patch` seamănă mult: ambele verifică datele, apoi le salvează sau afișează un avertisment.
  Pașii comuni sunt în `IngestionCommand`.
- **Răspunsurile:** un `get` poate reuși, poate să nu găsească date sau poate fi refuzat.
  Pentru că cele trei răspunsuri arată diferit, fiecare are clasa lui.

### Enum-uri

Le-am folosit acolo unde există o listă fixă de variante: tipurile de date, tipurile de
abonament, numele comenzilor, stările unui răspuns și mesajele de eroare. Astfel, o greșeală
de scriere este mai ușor de observat. Mesajele de eroare trebuie să fie exacte, deci e util să
fie toate în același loc.

### Alegerea clasei potrivite

Trei clase aleg ce obiect trebuie creat: o comandă, o verificare sau o valoare din piață.
Restul programului nu trebuie să se ocupe de această alegere.

### Protejarea datelor

Datele din interiorul claselor sunt private. Un interval de timp nu mai poate fi modificat după
ce a fost creat. Numerele cu semnificație fixă, cum ar fi limitele de preț, au nume, ca să fie
clar ce reprezintă.

### Păstrarea datelor

Doar `MarketDataRepository` știe cum sunt păstrate datele. Ele sunt grupate după tip și apoi
după zi, cu zilele în ordine. Așa se găsesc ușor cele mai recente date. O zi are 96 de locuri,
câte unul pentru fiecare sfert de oră. Un loc gol înseamnă că datele lipsesc.

### Un caz special

Dacă un `import` trimite o valoare greșită, locul respectiv se golește, chiar dacă avea deja o
valoare bună. Dacă greșeala vine de la `patch`, valoarea veche rămâne. Am făcut așa pentru că
acesta este rezultatul cerut de teste.

## Ce am schimbat pe parcurs

În prima variantă, lista tipurilor de date se ocupa și de crearea obiectelor și de alegerea
verificărilor. Codul era scurt, dar greu de urmărit. Am mutat aceste sarcini în clase separate
și am scris verificările într-un mod mai simplu.

## Folosirea instrumentelor AI

Am folosit AI și a fost implicat în:
- alegerea tipului de dată TreeMap, pentru ca ține cheile sortate automat și trebuie știută ordinea zilelor
- Ideea de clasă care păstrează doar data și indexul intrărilor, apoi prelucrează restul de date prin metode ale clasei(TimeSlot)
- ideaa de a salva doar momentul ultimei comenzi și dacă ingestia este pornită
- îmbunătățirea de coding style
