set terminal pngcairo size 800,600
set output 'error_frequency.png'
set title "Fehlerhäufigkeit über verschiedene Betriebssysteme"
set xlabel "Betriebssysteme"
set ylabel "Anzahl der Fehler"
set style data histograms
set style histogram cluster gap 1
set style fill solid border -1
set boxwidth 0.9
set yrange [0:*]
set grid ytics

plot 'data.txt' using 2:xtic(1) title 'Blue Screen', \
     '' using 3 title 'Black Screen', \
     '' using 4 title 'Red Screen'
