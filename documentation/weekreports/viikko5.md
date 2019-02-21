## Viikkoraportti viikolle 5

Tällä viikolla toteutin ohjelmaan monia pieniä käyttöliitymäominaisuuksia, kuten satunnaisen seedin luovan napin, 
syötteiden sanitoinnin ja virheviestit. Lisäksi muutin Perlin noise -algoritmin käyttämään käyttäjän antamaa seed-arvoa ja lisäsin
ohjelmaan mahdollisuuden tarkastella hieman statistiikkaa generoidusta taulukosta. Ohjelmassa alkaa pikkuhiljaa olemaan lähes 
kaikki ominaisuudet, joita olin alunperin suunnitellut. Tällä viikolla käytin vertaisarviontia huomioimatta aikaa noin 7 tuntia.

Haluaisin edelleen implementoida myös kolmiulotteisen melun generoinnin, mutta suurin vaikeus tässä on, miten generoitu taulukko 
oikeasti esitetään käyttäjälle. En usko ajan riittävän kolmiulotteista piirtämistä käyttävän visualisoinnin toteuttamiseen, 
mutta yritän keksiä sopivan keinon.

Ensi viikolla teen algoritmien kolmiulotteiset toteutukset, mikäli päädyn sisällyttämään ne projektiin ollenkaan. Lisäksi ajan
riittäessä yritän lisätä ohjelmaan statistikkaa ja kirjoitan dokumentaatiota eteenpäin. Myöskin fractal noise (käytännössä muiden
melualgoritmien yhdistäminen ja layeraaminen) voisi olla kiinnostava ja suhteellisen helppo lisäys, jonka saatan toteuttaa.

---

Ovatko Javan Math.sqrt ja System.nanoTime (nanoTime satunnaisgeneroinnin lähtöarvona) sallittuja tiralabraprojektissa? Voisin kyllä
teoriassa kirjoittaa oman neliöjuuren, mutta asiaa netistä tutkimassa sain käsityksen, että tämä ei ole kannattavaa, koska 
standardikirjaston toteutus perustuu jonkinlaiseen hardware-tukeen ja oma toteutus ei voisi ikinä olla yhtä nopea.
