# Testausdokumentti

Algoritmien hankalasti testattavan luonteen takia ohjelmaa on yksikkötestauksen osalta testattu hieman pintapuolisesti. Algoritmeille on toteutettu yksikkötestejä, jotka valvovat, että algoritmien palauttamat arvot ovat välillä 0-1, eri syötteillä palautetaan eri arvoja ja samoilla syötteillä palautetaan sama arvo.

Lisäksi algoritmien nopeutta on testattu empiirisesti. Testatessa kaksiulotteisia algoritmeja taulukon koolla 2048\*2048, skaalalla 0.05, Perlin noise -algoritmi generoi taulukon 4194304 arvoa noin 69 millisekunnissa, kun taas Worley noise -algoritmilla kestää yhdellä feature pointilla per solu noin 787 millisekuntia. Kun samoja syötteitä testataan Worley noisella ja kymmenellä feature pointilla per solu, generoinnissa kestää noin 4909 millisekuntia. 

Ohjelman toteutuksilla kaksiulotteinen Perlin noise -algoritmi on siis algoritmien vakioaikaisuudesta huolimatta noin kymmenen kertaa kaksiulotteista Worleytä nopeampi samoilla syötteillä ja yhdellä feature pointilla per solu. Feature pointtien määrää nostaessa Worley noisen aikavaativuus taas nousee jotakuinkin lineaarisesti suhteessa feature pointtien määrään. Varsinkin tällaisissa tilanteissa algoritmin aikavaativuutta voisi kuitenkin parantaa huomattavasti optimoimalla.

Kolmiulotteisia algoritmeja testatessa taulukon koolla 512\*512\*16 ja skaalalla 0.05, Perlin noise generoi taulukon 4194304 arvoa noin 182 millisekunnissa, kun taas Worley noisella kestää noin 2825 millisekuntia. Tästä voidaan siis huomata, että siirtyessä kaksiulotteisista pisteistä kolmiulotteisiin, Perlin noisen aikavaativuus nousee yli kaksinkertaiseksi ja Worley noisen yli kolminkertaiseksi, vaikka generoitavien pisteiden määrä on edelleen sama.

Myös algoritmien luomien pisteiden jakaumaa on testattu. Molemmat algoritmit generoivat liukulukuarvoja välilä 0-1. Testauksen perusteella voidaan huomata, että sekä kaksi- että kolmiulotteisten Perlin noise -taulukkojen arvojen jakauma on lähellä normaalijakaumaa. Kaksiulotteinen Worley noise suosii lähellä nollaa olevia (tummia) arvoja, kun taas kolmiulotteinen Worley noise suosii lähellä yhtä olevia (vaaleita) arvoja. Kuitenkin mitä enemmän kerroksia kolmiulotteisella Worleylla generoi samaan taulukkoon, sitä vähäisempää vaaleiden arvojen suosiminen on. Uskoisin, että tämä johtuu algoritmin poikkeuksellisesta toiminnasta, kun lähellä nollaa olevan pisteen naapurien laskennassa tarkistetaankin negatiivisia koordinaatteja, minkä takia nollasyvyyttä lähellä olevat pisteet ovat tavallista vaaleampia. Ohessa hieman kaavioita jakaumista.

Pääalgoritmien testauksen lisäksi ohjelmalle on toteutettu eri logiikkaluokkien tulosten oikeellisuutta vahtivia yksikkötestejä.
Kaiken kaikkiaan ohjelman logiikkaluokkien testien rivikattavuus on 95% ja haaraumakattavuus 94%.

![Kattavuusraportti](https://github.com/Tubaias/noisegen/blob/master/documentation/images/testcoverage.png)
