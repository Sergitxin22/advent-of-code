import * as fs from 'fs';

function loadData(leftArray: number[], rightArray: number[]): void {
    const data = fs.readFileSync('data.txt', 'utf-8');
    const lines = data.split('\n');

    lines.forEach(line => {
        const values = line.trim().split("   ");
        if (values.length >= 2) {
            const leftValue = parseInt(values[0], 10);
            const rightValue = parseInt(values[1], 10);
            leftArray.push(leftValue);
            rightArray.push(rightValue);
        }
    });

    leftArray.sort((a, b) => a - b);
    rightArray.sort((a, b) => a - b);
}

// --- Part One ---
function calculateTotalDistance(leftArray: number[], rightArray: number[]): number {
    let totalDistance = 0;

    for (let i = 0; i < leftArray.length; i++) {
        const distance = Math.abs(leftArray[i] - rightArray[i]);
        totalDistance += distance;
    }

    return totalDistance;
}

// --- Part Two ---
function calculateSimilarity(leftArray: number[], rightArray: number[]): number {
    let similarityScore = 0;

    for (let i = 0; i < leftArray.length; i++) {
        const searchedValue = leftArray[i];
        let counterAppearances = 0;

        rightArray.forEach(number => {
            if (searchedValue === number) {
                counterAppearances++;
            }
        });

        similarityScore += (counterAppearances * searchedValue);
    }

    return similarityScore;
}

// Función principal
function main() {
    const leftArray: number[] = [];
    const rightArray: number[] = [];

    loadData(leftArray, rightArray);

    console.log('Total distance between lists:', calculateTotalDistance(leftArray, rightArray));
    console.log('Similarity score:', calculateSimilarity(leftArray, rightArray));
}

main();
