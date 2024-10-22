# plot.gp

set terminal pngcairo size 800,600 enhanced font 'Verdana,10'
set output 'plot1.png'
set title 'Number of BSOD events by week, 2017'
set xlabel 'Week number'
set ylabel 'Number of BSOD events'
set boxwidth 0.5
set style fill solid
set grid
plot 'data1.txt' using 1:2 with boxes lc rgb 'black' notitle

set output 'plot2.png'
set title 'BSOD Events per Machine Month by Computer Version'
set xlabel 'Computer Version'
set ylabel 'BSOD Events per Machine Month'
set style data histograms
set style histogram cluster gap 1
set style fill solid border -1
set boxwidth 0.9
set xtics rotate by -45
plot 'data2.txt' using 2:xtic(1) title 'Other Video Adapter', \
     '' using 3 title 'Video Adapter A'

set output 'plot3.png'
set title 'BSODs per Machine Month for Most Frequent Event Types'
set xlabel 'Event Reference'
set ylabel 'BSODs per machine month'
set style data histograms
set style histogram cluster gap 1
set style fill solid border -1
set boxwidth 0.9
set xtics rotate by -45
plot 'data3.txt' using 2:xtic(1) title 'Other video adapter', \
     '' using 3 title 'Video adapter A'
