# Toteutusdokumentti

## Ohjelman rakenne

Ohjelma toimii tällä hetkellä yhden näkymän graafisessa käyttöliittymässä, jossa käyttäjä voi valita parametrit generoinnille
(käytetty algoritmi, kuvan leveys ja korkeus, melun skaala ja satunnaisgeneroinnin lähtöarvo). Generointinappia painaessa ohjelma
generoi halutuilla parametreilla ja melualgoritmilla taulukon, joka sitten piirretään näytölle grayscale-kuvana. Lisäksi ohjelmaan saa nappia painamalla näkyviin statistiikkaa generoidusta taulukosta, kuten generointiin kestänyt aika ja taulukon arvojen jakautuminen.

## Algoritmien analyysi

Melualgoritmit ottavat syötteenä pisteen ja generoivat pistettä vastaavan meluarvon. Ohjelman algoritmit ovat tällä hetkellä
toteutettu generoimaan meluarvoja kaksiulotteisille ja kolmiulotteisille pisteille. 

Sekä Worley noise, että Perlin noise -algoritmien toteutukset käyttävät ainostaan vakioaikaisia operaatioita, ja molempien 
algoritmien aikavaativuus on rajoitetulla ulottuvuuksien määrällä yksittäisille pisteille luokkaa O(1). Käytännössä algoritmien tuloksia ei kuitenkaan käytetä yksittäisinä arvoina, vaan jonkinlaisessa taulukossa. Algoritmien O(1)-luokan aikavaativuudesta johtuen kaksiulotteisin n\*m-kokoisen taulukon generoimisen aikavaativuus on siis luokkaa O(n\*m) ja kolmiulotteisen n\*m\*o-kokoisen taulukon luokkaa O(n\*m\*o) molemmilla algoritmeilla.

Kiinnostavampi tulos algoritmien aikavaativuudesta on kuitenkin niiden skaalautuminen *n* ulottuvuuteen. Perlin noise -algoritmin aikavaativuus on tunnetusti luokkaa O(2^n) *n* ulottuvuudella. Worley noisen aikavaativus taas on toteutuksen tutkimisen perusteella luokkaa O(3^n) *n* ulottuvuudella.

Vaikka molemmat algoritmit ovat vakioaikaisia, algoritmien nopeudella on käytännössä kuitenkin merkittävä ero. Testatessa kaksiulotteisia algoritmeja taulukon koolla 2048\*2048, skaalalla 0.1, Perlin noise -algoritmi generoi taulukon 4194304 arvoa noin 70 millisekunnissa, kun taas Worley noise -algoritmilla kestää noin 850 millisekuntia. Ohjelman toteutuksilla kaksiulotteinen Perlin noise -algoritmi on siis algoritmien vakioaikaisuudesta huolimatta noin kymmenen kertaa kaksiulotteista Worleytä nopeampi samoilla syötteillä.

Kolmiulotteisia algoritmeja testatessa taulukon koolla 2048\*2048\*1 ja skaalalla 0.1, Perlin noise generoi taulukon 4194304 arvoa noin 200 millisekunnissa, kun taas Worley noisella kestää noin 3100 millisekuntia. Tästä voidaan siis huomata, että siirtyessä kaksiulotteisista pisteistä kolmiulotteisiin, Perlin noisen aikavaativuus nousee yli kaksinkertaiseksi ja Worley noisen yli kolminkertaiseksi, vaikka generoitavien pisteiden määrä on edelleen sama. Algoritmit ovat siis lähellä aikavaativuuksien mukaista skaalautumista kolmanteen ulottuvuuteen.

Tässä täytyy kuitenkin ottaa myös huomioon, että ohjelman Worley noise -toteutus on tällä hetkellä variaatio, joka käyttää tällä
hetkellä vain yhtä 'feature pointtia' per generoinnin solu, ja Steven Worleyn alkuperäinen artikkeli ehdottaa useampiin 'feature 
pointteihin' perustuvia optimointimenetelmiä algoritmille [1].

Molempien algoritmien tilavaativuudet ovat luokkaa O(1) ja käytännössä triviaaleja. Perlin noise -algoritmin tilavaativuus on
hieman Worley noisea suurempi, koska Perlinin algoritmi käyttää hajautusarvojen generointiin kovakoodattua taulukkoa, joka pitää
sisällään 512 kokonaislukuarvoa.

## Työn puutteet

Vaikka algoritmit voi toteuttaa skaalautumaan n-ulottuvuuteen, ohjelmassa on tällä hetkellä mahdollisuus vain kaksiulotteisen
melun generointiin. Lisäksi satunnaisgeneroinnin lähtöarvoa (seed) ei käytetä tällä hetkellä Perlin noise -algoritmissa ollenkaan
ja Worley noise algoritmi on toteutettu käyttämään vain yhtä 'feature pointtia' per generoinnin solu.

## Lähteet

1. [A Cellular Texture Basis Function](https://dl.acm.org/citation.cfm?id=237267) (Steven Worley 1996)
