import * as fs from 'fs';

function loadData(numberLists: number[][]): void {
    const data = fs.readFileSync('data.txt', 'utf-8');
    const lines = data.split('\n');

    lines.forEach(line => {
        const values = line.trim().split(" ");

        let lineNumbers: number[] = [];
        values.forEach(number => {
            lineNumbers.push(Number(number.trim()));
        });

        numberLists.push(lineNumbers);
    });
}

// --- Part One ---
function safeReports(numberLists: number[][]): number {
    let count: number = 0

    numberLists.forEach(numberList => {
        if (isSafeReport(numberList)) {
            count++;
        }
    });

    return count;
}

function isSafeReport(numberList: number[]): boolean {
    let isGrowing: boolean = false;
    let firstNumber: number = numberList.at(0) ?? 0;
    let secondNumber: number = numberList.at(1) ?? 0;

    if (firstNumber - secondNumber < 0) {
        isGrowing = true;
    }

    for (let i = 0; i < numberList.length - 1; i++) {
        let currentNumber: number = numberList.at(i) ?? 0;
        let nextNumber: number = numberList.at(i + 1) ?? 0;

        let currentNumbersAreGrowing: boolean = false;

        if ((currentNumber - nextNumber) < 0) {
            currentNumbersAreGrowing = true;
        }

        if (currentNumbersAreGrowing != isGrowing)
            return false;

        const difference: number = Math.abs(currentNumber - nextNumber);

        if (difference < 1 || difference > 3)
            return false;
    }

    return true;
}

// --- Part Two ---
function safeReportsWithTolerance(numberLists: number[][]): number {
    let count: number = 0;

    numberLists.forEach(numberList => {
        if (isSafeReportWithTolerance(numberList)) {
            count++;
        }
    });

    return count;
}

function isSafeReportWithTolerance(numberList: number[]): boolean {
    if (isSafeReport(numberList)) {
        return true;
    }

    for (let i = 0; i < numberList.length; i++) {
        let sublist: number[] = [];

        sublist = sublist.concat(numberList.slice(0, i));
        sublist = sublist.concat(numberList.slice(i + 1));

        if (isSafeReport(sublist)) {
            return true;
        }
    }

    return false;
}

// Función principal
function main() {
    let numberLists: number[][] = [];
    loadData(numberLists);

    console.log('Safe reports:', safeReports(numberLists));
    console.log('Safe reports with tolerance:', safeReportsWithTolerance(numberLists));
}

main();