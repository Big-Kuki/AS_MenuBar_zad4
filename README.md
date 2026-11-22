# Licznik z paskiem

Aplikacja Android prezentująca licznik z obsługą menu w Toolbarze.

## Funkcjonalności
- **Toolbar z tytułem "Licznik"**: Domyślny tytuł paska narzędzi.
- **Wyświetlacz licznika**: Duża liczba na środku ekranu prezentująca aktualną wartość.
- **Menu w Toolbarze**:
  - `+1` (ikona dodawania) – zwiększa licznik o 1 (`showAsAction="always"`).
  - `-1` (ikona odejmowania) – zmniejsza licznik o 1 (`showAsAction="always"`).
  - `Zeruj` (w overflow menu pod trzema kropkami, `showAsAction="never"`) – po potwierdzeniu w `AlertDialog` resetuje licznik do 0.
- **Dynamiczny tytuł paska**: Przy każdej zmianie wartości licznika tytuł paska zmienia się na `Licznik: N`.
- **Zabezpieczenie przed wartościami ujemnymi**: Licznik nie schodzi poniżej zera – przy próbie odejmowania poniżej 0 pojawia się komunikat `Toast`.

---

## Odpowiedź na pytanie: co było trudniejsze – menu czy zmiana tytułu paska?

**Stworzenie menu było trudniejsze niż zmiana tytułu paska.**

### Uzasadnienie:
1. **Menu**:
   - Wymagało utworzenia dedykowanego pliku zasobów XML (`res/menu/main_menu.xml`).
   - Wymagało przygotowania ikon wektorowych (`ic_add.xml` oraz `ic_remove.xml`).
   - Wymagało poprawnego skonfigurowania atrybutów `app:showAsAction` (`always` dla akcji dodawania/odejmowania oraz `never` dla opcji zerowania w overflow menu).
   - Wymagało nadpisania metod cyklu życia aktywności (`onCreateOptionsMenu` oraz `onOptionsItemSelected`).
   - Pozycja „Zeruj” wymagała dodatkowo zbudowania i wyświetlenia okna dialogowego (`AlertDialog`) z obsługą przycisków potwierdzenia.

2. **Zmiana tytułu paska**:
   - Sprowadzała się do wywołania prostej metody `getSupportActionBar().setTitle("Licznik: " + count)` (lub `toolbar.setTitle(...)`) przy każdej aktualizacji stanu licznika.
