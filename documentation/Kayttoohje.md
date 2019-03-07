# Käyttöohje

## Ohjelman ajaminen

Lataa ohjelman jar-tiedosto repositoriossa olevasta [releasesta](https://github.com/Tubaias/noisegen/releases/tag/v1.0) ja aja se
tuplaklikkaamalla. Ohjelman ajamista varten koneellasi täytyy olla [Java](https://www.java.com/en/download/).

## Ohjelman käyttäminen

![UI](https://github.com/Tubaias/noisegen/blob/master/documentation/images/UI-example.png "3D Worley noise")

Ohjelmassa on graafinen käyttöliittymä. Vasemmalla ylhäällä olevilla radionapeilla voi kontrolloida käytettyä algoritmia, generoinnin ulottuvuutta ja tuloksen piirtotyyliä. 
Tekstikenttiin voi kirjoittaa generoinnin parametreja, joista ainoa maininnanarvoinen on "scale", joka toimii arvoilla välillä yhdestä nollaan ja säätelee generoitavan melukuvion skaalaa suhteessa kuvan kokoon.

'Random seed' -nappi luo satunnaisen lähtöarvon generoinnille, 'generate noise image' -nappi generoi kuvan annetuilla parametreillä, 'image statistics' -nappi avaa uuden ikkunan, jossa näytetään statistiikkaa ja 'save to file(s)' -nappi tallentaan nykyisen melutaulukon yhteen tai useampaan PNG-tiedostoon uuteen kansioon nimeltä 'noisegen-images'.

Kun generoidaan kolmiulotteinen kuva, kuvan alle tulee näkyviin liukusäädin, jolla voi säädellä mikä kuvan syvyyssuuntainen läpileikkaus piirretään. Lisäksi 'animate 3D noise' -napilla voi kerrosten läpi kelata automaattisesti.
