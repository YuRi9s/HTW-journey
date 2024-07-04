CXX = g++
CXXFLAGS = -std=c++11 -Wall
SRCS = Dialog.cpp Mathe.cpp Random.cpp Arrays.cpp
OBJS = $(SRCS:.cpp=.o)
TARGET = dialog

$(TARGET): $(OBJS)
	$(CXX) $(CXXFLAGS) -o $@ $^

%.o: %.cpp
	$(CXX) $(CXXFLAGS) -c $<

run: $(TARGET)
	./$(TARGET)

clean:
	rm -f $(OBJS) $(TARGET)
