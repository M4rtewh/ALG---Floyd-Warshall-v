# Floy-Warshallův Algoritmus
Floyd-Warshall je robustní algoritmus z oblasti **dynamického programování**, který řeší problém hledání nejkratších cest v grafu.

---

###  1. Základ

- **Hlavní účel:** Nalezení nejkratších cest mezi **všemi dvojicemi vrcholů**.
- **Typ grafu:** Pracuje na **orientovaných** i **neorientovaných** grafech.
- **Váhy hran:** Zvládá kladné i **záporné váhy** (na rozdíl od Dijkstry).
- **Detekce cyklů:** Dokáže identifikovat **záporné cykly** v grafu.

---

### 2. Matematika

Algoritmus postupně vylepšuje odhad vzdálenosti mezi vrcholy $i$ a $j$ tím, že zvažuje průchod přes pomocný vrchol $k$.

**Rekurentní vzorec:**

> $$d_{i,j}^{(k)} = \min(d_{i,j}^{(k-1)}, d_{i,k}^{(k-1)} + d_{k,j}^{(k-1)})$$

-  $d_{i,j}^{(k)}$: Nejkratší cesta z $i$ do $j$ s použitím vrcholů $\{1...k\}$ jako mezilehlých bodů.

- **Logika:** Buď je stávající cesta nejlepší, nebo existuje lepší cesta vedoucí přes uzel $k$.


---

###  3. Složitost

Vzhledem ke třem vnořeným cyklům je složitost algoritmu predikovatelná a nezávislá na počtu hran.

|**Typ složitosti**|**Zápis**|**Popis**|
|---|---|---|
|**Časová**|$O(V^3)$|Kde $V$ je počet vrcholů.|
|**Prostorová**|$O(V^2)$|Nutnost uchovávat matici vzdáleností $V \times V$.|

---

###  4. Příklad (Java)

Jádro algoritmu spočívá v elegantní trojité smyčce:

```
/**
 * Implementace jádra Floyd-Warshallova algoritmu
 * @param dist Matice sousednosti inicializovaná vahami hran
 * @param V Počet vrcholů v grafu
 */
for (int k = 0; k < V; k++) {  
    System.out.println("Iterace " + (k + 1) + ": Použití vrcholu " + k + " jako prostředníka.");  
    
    for (int i = 0; i < V; i++) {  
        for (int j = 0; j < V; j++) {  
            // Kontrola existence cesty přes k a případná aktualizace
            if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {  
                    dist[i][j] = dist[i][k] + dist[k][j];  
                }
            }
        }  
    }  
    printMatrix(dist, V); // Pomocná metoda pro výpis stavu
}
```
### 4.1. Výsledek 
```
Original Adjacency Matrix:
0   5   INF 10   
INF 0   3   INF 
INF INF 0   1   
INF INF INF 0   
---------------------------
Iteration 1: Using vertex 0 as intermediate.
0   5   INF 10   
INF 0   3   INF 
INF INF 0   1   
INF INF INF 0   
---------------------------
Iteration 2: Using vertex 1 as intermediate.
0   5   8   10   
INF 0   3   INF 
INF INF 0   1   
INF INF INF 0   
---------------------------
Iteration 3: Using vertex 2 as intermediate.
0   5   8   9   
INF 0   3   4   
INF INF 0   1   
INF INF INF 0   
---------------------------
Iteration 4: Using vertex 3 as intermediate.
0   5   8   9   
INF 0   3   4   
INF INF 0   1   
INF INF INF 0   
---------------------------
```
---

###  5. Záporné cykly

Záporný cyklus způsobuje, že nejkratší cesta neexistuje (lze ji zmenšovat do nekonečna). Floyd-Warshall tento problém odhalí snadno:

- Po doběhnutí algoritmu zkontroluj **hlavní diagonálu** matice.
- Pokud platí: $d_{i,i} < 0$ pro libovolné $i$, graf **obsahuje záporný cyklus**.

---

###  6. Využití

1. **Směrování v sítích:** Výpočet optimálních cest v IP sítích.
2. **Dopravní plánování:** Matice vzdáleností mezi všemi městy v navigacích.
3. **Tranzitivní uzávěr:** Zjištění, zda jsou uzly vzájemně dosažitelné (Warshallova varianta).
4. **Hledání regulárních výrazů:** Převod konečných automatů na regulární výrazy (Kleeneho algoritmus).

---

###  Git

#### **Repozitář projektu:** [Github - ALG Floyd-Warshall](https://github.com/M4rtewh/ALG---Floyd-Warshall-v/tree/master)
