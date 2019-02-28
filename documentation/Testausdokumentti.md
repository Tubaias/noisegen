# Testausdokumentti

Ohjelmaa on yksikkötestauksen osalta testattu melko pintapuolisesti. Algoritmeille on toteutettu yksikkötestejä, jotka valvovat, että algoritmien palauttamat arvot ovat välillä 0-1, eri syötteillä palautetaan eri arvoja ja samoilla syötteillä palautetaan sama arvo.

Lisäksi algoritmien nopeutta on testattu hieman empiirisesti. Testatessa kaksiulotteisia algoritmeja taulukon koolla 2048\*2048, skaalalla 0.1, Perlin noise -algoritmi generoi taulukon 4194304 arvoa noin 70 millisekunnissa, kun taas Worley noise -algoritmilla kestää noin 850 millisekuntia. Ohjelman toteutuksilla kaksiulotteinen Perlin noise -algoritmi on siis algoritmien vakioaikaisuudesta huolimatta noin kymmenen kertaa kaksiulotteista Worleytä nopeampi samoilla syötteillä.

Kolmiulotteisia algoritmeja testatessa taulukon koolla 2048\*2048\*1 ja skaalalla 0.1, Perlin noise generoi taulukon 4194304 arvoa noin 200 millisekunnissa, kun taas Worley noisella kestää noin 3100 millisekuntia. Tästä voidaan siis huomata, että siirtyessä kaksiulotteisista pisteistä kolmiulotteisiin, Perlin noisen aikavaativuus nousee yli kaksinkertaiseksi ja Worley noisen yli kolminkertaiseksi, vaikka generoitavien pisteiden määrä on edelleen sama.

Pääalgoritmien testauksen lisäksi ohjelmalle on toteutettu eri logiikkaluokkien tulosten oikeellisuutta vahtivia yksikkötestejä.
Kaiken kaikkiaan ohjelman logiikkaluokkien testien rivikattavuus on 96% ja haaraumakattavuus 95%.

![Kattavuusraportti](https://github.com/Tubaias/noisegen/blob/master/documentation/images/testcoverage.png)
