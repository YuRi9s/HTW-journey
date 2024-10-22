# Compiler
CXX = g++

# Compiler flags
CXXFLAGS = -std=c++11 -Wall -Wextra -pedantic

# Target executable
TARGET = library_system

# Source files
SRCS = main.cpp Author.cpp Publication.cpp Book.cpp Magazine.cpp Shelf.cpp BookShelf.cpp MagazineShelf.cpp Customer.cpp Library.cpp RandomGenerator.cpp

# Object files directory
OBJDIR = sus

# Object files
OBJS = $(SRCS:%.cpp=$(OBJDIR)/%.o)

# Default target
all: $(OBJDIR) $(TARGET)

# Create the object files directory
$(OBJDIR):
	mkdir -p $(OBJDIR)

# Link object files to create the executable
$(TARGET): $(OBJS)
	$(CXX) $(CXXFLAGS) -o $(TARGET) $(OBJS)

# Compile source files to object files
$(OBJDIR)/%.o: %.cpp
	$(CXX) $(CXXFLAGS) -c $< -o $@

# Clean up build files
clean:
	rm -rf $(OBJDIR) $(TARGET)

# Phony targets
.PHONY: all clean
