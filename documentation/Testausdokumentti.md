# Testausdokumentti

Ohjelmaa on tällä hetkellä testattu melko pintapuolisesti. Algoritmeille on toteutettu yksikkötestejä, jotka valvovat, että
algoritmien palauttamat arvot ovat välillä 0-1, eri syötteillä palautetaan eri arvoja ja samoilla syötteillä palautetaan sama arvo.

Lisäksi algoritmien nopeutta on testattu hieman empiirisesti. Testatessa ohjelmaa taulukon koolla 2048*2048, skaalalla 0.1 
ja generoinnin lähtöarvolla 1, Perlin noise -algoritmi generoi taulukon 4194304 arvoa noin 85 millisekunnissa, kun taas Worley 
noise -algoritmilla kesti noin 850 millisekuntia. Ohjelman toteutuksilla Perlin noise -algoritmi oli siis algoritmien 
vakioaikaisuudesta huolimatta noin kymmenen kertaa nopeampi samoilla syötteillä.

Pääalgoritmien testauksen lisäksi ohjelmalle on toteutettu eri logiikkaluokkien tulosten oikeellisuutta vahtivia yksikkötestejä.
Kaiken kaikkiaan ohjelman logiikkaluokkien testien rivikattavuus on 98% ja haaraumakattavuus 90%.

![Kattavuusraportti](https://github.com/Tubaias/noisegen/blob/master/documentation/images/testcoverage.png)
