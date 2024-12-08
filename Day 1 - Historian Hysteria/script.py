def load_data(left_list, right_list):
    with open("data.txt", "r") as file:
        for line in file:
            line_numbers = line.split("   ")
            left_num = line_numbers[0].strip()
            right_num = line_numbers[1].strip()

            left_list.append(left_num)
            right_list.append(right_num)
            # print(f"{left_num} -> {right_num}")

# --- Part One ---
def calculate_total_distance(list1, list2):
    list1 = sorted(list1)
    list2 = sorted(list2)
    total_distance = 0

    for i in range(len(list1)):
        # print(f"########### {i+1} ##########")
        distance = abs(int(list1[i]) - int(list2[i]))
        # print(distance)
        total_distance += distance

    return total_distance

left_list = []
right_list = []
load_data(left_list, right_list)
print(f"Total distance between lists: {calculate_total_distance(left_list, right_list)}")

# --- Part Two ---
def calculate_similarity(list1, list2):
    similarity_score = 0

    while len(list1) > 0:
        searched_value = list1[0]
        counter_appearances = list2.count(searched_value)
        list1.pop(0)
        similarity_score += (counter_appearances * int(searched_value))
        # print(f"Value {searched_value} -> appears {counter_appearances} times")
    
    return similarity_score


print(f"Similarity score: {calculate_similarity(left_list, right_list)}")