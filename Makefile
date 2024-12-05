# Maven commands
MVN := mvn
JAVA := java

# Project configuration
MAIN_CLASS := com.Main

# Default target
.DEFAULT_GOAL := help

# Cleaning build directories
clean:
	@echo "Cleaning project..."
	@$(MVN) clean

# Compile the project
compile:
	@echo "Compiling project..."
	@$(MVN) compile

# Run all tests
test: compile
	@echo "Running all tests..."
	@$(MVN) test

# Run specific test class (usage: make test-class CLASS=GestorSalasTest)
test-class:
	@echo "Running test class $(CLASS)..."
	@$(MVN) -Dtest=$(CLASS) test

# Run specific test method (usage: make test-method CLASS=GestorSalasTest#testAgregarSala)
test-method:
	@echo "Running test method $(CLASS)..."
	@$(MVN) -Dtest=$(CLASS) test

# Package the application
package: clean compile test
	@echo "Packaging application..."
	@$(MVN) package

# Install dependencies
deps:
	@echo "Installing dependencies..."
	@$(MVN) dependency:resolve

# Run the application
run: package
	@echo "Running application..."
	@$(MVN) exec:java -Dexec.mainClass="$(MAIN_CLASS)"

# Generate project reports
report:
	@echo "Generating Maven project reports..."
	@$(MVN) site

# Help target
help:
	@echo "Available targets:"
	@echo "  clean      - Clean build directories"
	@echo "  compile    - Compile the project"
	@echo "  test       - Run all tests"
	@echo "  test-class - Run specific test class (usage: make test-class CLASS=GestorSalasTest)"
	@echo "  test-method- Run specific test method (usage: make test-method CLASS=GestorSalasTest#testAgregarSala)"
	@echo "  package    - Package the application"
	@echo "  deps       - Install dependencies"
	@echo "  run        - Run the application"
	@echo "  report     - Generate project reports"
	@echo "  help       - Show this help message"

.PHONY: clean compile test test-class test-method package deps run report help