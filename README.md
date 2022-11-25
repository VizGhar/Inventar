# Inventár [SK]

Veľmi rýchly návrh aplikácie s lokálnou databázou aj s vysvetlením pre začínajúceho Android programátora, ktorý má aspoň základné predchádzajúce skúsenosti s akýmkoľvek programovaním.

## Ako na to?

### Aktivity (`Activity`) a vizuály (`layouts`)

Každá obrazovka je aktivita. Napr. `MainActivity` je hlavná obrazovka, `InventoryActivity` je obrazovka s inventárnym zoznamom. Každá aktivita má nejaký vizuál ktorý je definovaný v `res/layout/xml` súbore. Napr. `MainActivity` má vizuál v `activity_main.xml`.

Keď chceš vytvoriť novú Aktivitu. Odporúčam jednoducho zvoliť File > New > Activity > EmptyActivity. To vytvorí aj layout.xml aj activity.kt aj pridá túto aktivitu do Manifestu (ten nebudem vysvetľovať)

V xmlku používame komponenty ako sú `Button` (tlačidlo), `TextView` (text), `EditText` (editovateľný text). Tieto "malé komponenty" tzv. `View` sa nachádzajú v kontajneroch `ViewGroup` alebo skôr `Layout`. Napríklad `LinearLayout` alebo `ConstraintLayout`.  Rýchle vysvetlenie:

- [`LinearLayout`](https://developer.android.com/develop/ui/views/layout/linear) - Umiestňuje View pod seba (orientation = vertical) alebo vedľa seba (horizontal)
- [`FrameLayout`](https://www.geeksforgeeks.org/framelayout-in-android/) - Umiestňuje View podľa "gravitácie" napr. layout_gravity="center" vycentruje tento View presne na stred
- [`ConstraintLayout`](https://developer.android.com/develop/ui/views/layout/constraint-layout) - Ten sa používa najviac, ale je najťažší na pochopenie
- [`RecyclerView`](https://developer.android.com/develop/ui/views/layout/recyclerview) - Zobrazuje dynamický počet položiek pod sebou alebo v mriežke. Toto sa hodí hlavne pre zoznamy položiek. Je veľmi ťažký na implementáciu pre začiatočníka.

### `ViewModel`-y a `LiveData`

`ViewModel` obsahuje všetku logiku. Napríklad čítanie z databázy (`InventoryViewModel`) alebo zložitejšie výpočty (práca s DB je povinná na inom vlákne). Vo `ViewModel` si vytvoríme metódy pre spracovanie a tie ukladajú hodnoty do `LiveData` (alebo skôr `MutableLiveData`). Pozri `InventoryViewModel`

Takže implementácia logiky pre výpočty vo ViewModel vyzerá nasledovne:
1. ViewModel - vytvoriť funkciu, ktorú chcem volať z aktivity (napríklad si sem pošlem položku, ktorú chcem pridať do zoznamu)
```
fun add(item: String) {  
}
```
2. ViewModel - ak potrebujem asynchrónne spracovanie, spustím prácu na ďalšom vlákne
```
fun add(item: String) {  
    viewModelScope.launch(Dispatchers.IO) {  
  
 }}
```
3. ViewModel - Ak potrebujem zistiť výsledok z tejto funkcie, vytvorím si pre tento výsledok MutableLiveData
```
val complete = MutableLiveData(false)
```
4.  ViewModel - A výsledok do týchto live dát uložím
```
complete.postValue(true)
```
5. Activity - zavolám funkciu z ViewModel kedy potrebujem a počúvam na zmeny v LiveData nasledovne:
```
viewModel.complete.observe(this) { complete ->  
  if (complete) {
    // hotovo  
  } else {
    // nehotovo
  }
}
```