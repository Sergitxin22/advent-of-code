def load_data(number_lists):
    with open("data.txt", "r") as file:
        for line in file:
            line_numbers = line.split(" ")
            line_numbers[len(line_numbers)-1] = line_numbers[len(line_numbers)-1].strip()
            number_lists.append(line_numbers)

# --- Part One ---
def safe_reports(number_lists):
    count = 0
    for number_list in number_lists:
        if is_safe_report(number_list):
            count += 1
    return count

def is_safe_report(number_list):
    is_growing = False
    first_number = int(number_list[0])
    second_number = int(number_list[1])
        
    if first_number - second_number < 0:
        is_growing = True

    for i in range(len(number_list) - 1):
        current_number = int(number_list[i])
        next_number = int(number_list[i+1])

        current_numbers_are_growing = False
        
        if (current_number - next_number) < 0:
            current_numbers_are_growing = True
        
        if current_numbers_are_growing != is_growing:
            return False

        difference = abs(current_number-next_number)
        
        if difference < 1 or difference > 3:
            return False

    return True

# --- Part Two ---
def safe_reports_with_tolerance(number_lists):
    count = 0
    for number_list in number_lists:
        if is_safe_report_with_tolerance(number_list):
            count += 1
    return count

def is_safe_report_with_tolerance(number_list):
    if is_safe_report(number_list):
        return True

    for i in range(len(number_list)):
        sublist = number_list[:i] + number_list[i+1:]
        if is_safe_report(sublist):
            return True
    
    return False

number_lists = []
load_data(number_lists)

safe_reports = safe_reports(number_lists)
print(f"Safe reports: {safe_reports}")

safe_reports_with_tolerance = safe_reports_with_tolerance(number_lists)
print(f"Safe reports with tolerance: {safe_reports_with_tolerance}")