# Määrittelydokumentti

## Tavoite ja algoritmit

Ohjelman tarkoitus on vertailla erilaisten melualgoritmien käyttöä proseduraalisessa maastongeneroinnissa.
Tarkasteltaviksi algoritmeiksi on valittu Perlin noise, Worley noise ja mahdollisesti myös Simplex noise.
Valinnan syynä on lähinnä algoritmien tunnettuus.

## Syötteet ja ohjelman toiminta

Ohjelman käynnistämisen jälkeen käyttäjä voi valita voi valita miten suuri alue generoidaan ja mitä algoritmia ohjelma käyttää.
Tämän perusteella ohjelma generoidun halutun kokoisen alueen melualgoritmilla ja laskee generointiin käytetyn ajan. 
Generoitu alue näytetään käyttäjälle joko kaksiulotteisena, tai projektiin käytetyn ajan salliessa kolmiulotteisena, korkeuskarttana.

## Aika- ja tilavaativuudet

Perlin noise -algoritmin aikavaativuus on luokkaa O(2^n) *n* ulottuvuudella, kun taas Simplex noise -algoritmin aikavaativuus on
luokkaa O(n^2) *n* ulottuvuudella. Worley noise -algoritmille en pystynyt löytämään dokumentoitua aikavaativuutta, joten tämä täytyy
selvittää myöhemmin, kun algoritmin tarkempi toteutus on tiedossa. Algoritmien tilavaativuudesta ei myöskään löytynyt dokumentoitua
tietoa, mutta algoritmien luonteen vuoksi tilavaativuuden voisi olettaa olevan jokseenkin triviaali. Tämäkin täytyy kuitenkin
selvittää myöhemmin.

## Lähteet

[Terrain synthesis using noise](https://tampub.uta.fi/bitstream/handle/10024/101043/GRADU-1494236249.pdf) (Tuomo Hyttinen 2017)

