import os
import time
import datetime

product_format = ["Product id", "Name", "Description", "Price", " Quantity"]
order_format = ["Order id", "Product id", "Order_quantity", "Order date"]
supplier_format = ["Supplier id", "Name", "Contact details"]
stock_count = 0
total_sales = 0
format_total_sales = 0
count_number= 0


# for confirm data in special place
def replace_format_product(product_id, name, description, price, quantity):
    if len(product_format) < 5:
        raise ValueError("product_format must have at least 5 elements.")

    product_format[0] = str(product_id)
    product_format[1] = str(name)
    product_format[2] = str(description)
    product_format[3] = str(price)
    product_format[4] = str(quantity)
    return product_format


# receive the data from file
def read_data_from_file(file_path):
    try:
        with open(file_path, "r") as file:
            return file.readlines()
    except FileNotFoundError:
        return []


# check the data have not included
def check_product_valid(lines, product_id):
    for line in lines:
        try:
            data = eval(line.strip())
            if data[0] == product_id:
                print(f"!  Product ID '{product_id}'already exists  !")
                return False
        except ValueError:
            print(f"Invalid: {line.strip()}")
            continue

    return True  # If no match is found


# for locate where data
def find_location(lines, target):
    for line in lines:
        try:
            # Parse the line as JSON
            data = eval(line.strip())

            # Validate that data is a list with at least 5 elements
            if target == data[0]:
                print(f"  [1] Product id : {data[0]} \n"
                      f"  [2] Name : {data[1]} \n"
                      f"  [3] Description : {data[2]} \n"
                      f"  [4] Price : {data[3]} \n"
                      f"  [5] Quantity : {data[4]} \n")
                return {
                    "Product ID": data[0],
                    "Name": data[1],
                    "Description": data[2],
                    "Price": data[3],
                    "Quantity": data[4],
                }
        except ValueError:
            print(f"Invalid: {line.strip()}")
            continue

    print("Product ID not found")
    return None  # If the target is not found


def update_detail(lines, target, who, update, file_path="products.txt"):
    updated_lines = []
    for line in lines:
        try:
            # Strip the line and convert it to a list
            data = eval(line.strip())

            if target == data[0]:
                if who == "1":
                    data[0] = f"{update}"
                if who == "2":
                    data[1] = f"{update}"
                if who == "3":
                    data[2] = f"{update}"
                if who == "4":
                    data[3] = f"{update}"
                if who == "5":
                    data[4] = f"{update}"
                if who.lower() == "nope":
                    break
            updated_lines.append(str(data) + "\n")
        except ValueError:
            print(f"Invalid: {line.strip()}")
            continue

    # Write updated lines to the file
    try:
        with open(file_path, "w") as f:
            f.writelines(updated_lines)
        print("Product details updated successfully.")
    except ValueError:
        print(f"An error occurred while writing to the file")


# Function to format supplier data
def supplier_format_order(sd,nm,cd):
    supplier_format[0] = f"{sd}"
    supplier_format[1] = f"{nm}"
    supplier_format[2] = f"{cd}"
    return supplier_format


# for adding order
def replace_format_order(id, pd, qt, od):
    order_format[0] = f"{id}"
    order_format[1] = f"{pd}"
    order_format[2] = f"{qt}"
    order_format[3] = f"{od}"
    return order_format


# for count the store after order 
def dynamic_stock_counter(lines, product_id, order_quantity):
    for line in lines:
        data = eval(line.strip())

        if data[0] == product_id:
            quantity = int(data[4])
            counter = quantity - int(order_quantity)
            return counter

    return None


while True:
    print("=" * 40)
    print("Home".center(40))
    print("="*40)
    ans = input("[1] Add New Product \n"
                "[2] Update Product Detail \n"
                "[3] Add Supplier \n"
                "[4] Place An Order \n"
                "[5] View Inventory \n"
                "[6] Generate Reports \n"
                "[7] Exit \n"
                "Please enter a number for function : ")

    if ans == "1":  # Function of Add new product
        product_id = input("Product ID: ")
        file_path = "products.txt"  # provide the file path for check
        lines = read_data_from_file(file_path)

        if not check_product_valid(lines, product_id):
            continue  # Back to home if product ID exists

        name = input("Name: ")
        descript = input("Description: ")

        # Validate price
        while True:
            try:
                price = float(input("Price: "))
                if price < 0:
                    raise ValueError
                break
            except ValueError:
                print("Invalid input. Please enter a non-negative number for price.")
            except TypeError:
                name = input("Name: ")
                descript = input("Description: ")

                while True:
                    try:
                        price = float(input("Price: "))
                        if price < 0:
                            print("Invalid input. Please enter a non-negative number for price.")
                        else:
                            print("Valid price entered:", price)
                            break
                    except ValueError:
                        print("Invalid input. Please enter a numeric value.")

        # Validate quantity
        while True:
            try:
                quantity = int(input("Quantity: "))
                if quantity < 0:
                    raise ValueError
                break
            except ValueError:
                print("Invalid input. Please enter a non-negative integer for quantity.")
            except TypeError:
                name = input("Name: ")
                descript = input("Description: ")

                while True:
                    try:
                        quantity = int(input("Quantity: "))
                        if quantity < 0:
                            raise ValueError
                        break
                    except ValueError:
                        print("Invalid input. Please enter a non-negative integer for quantity.")

        # Add product to file
        new_format = replace_format_product(product_id, name, descript, price, quantity)
        with open(file_path, "a") as f:
            f.write(f"{new_format}\n")
        print("Product added successfully!")
        

    elif ans == "2":  # update new data function
        product_id = input("Enter Product id to search: ")

        # reading the file and storing lines
        file_path = "products.txt"
        lines = read_data_from_file(file_path)

        # Searching for the target
        if not find_location(lines, product_id):
            continue
        print(" ! Notice : if you wish to skip , simply enter 'Nope' !")

        while True:
            field_to_update = input("Please enter the number for the detail to update: ")

            if field_to_update.lower() == "nope":  # incase not update message
                continue  # back to home
            elif field_to_update in ['1', '2', '3', '4', '5']:
                break
            else:
                print("Invalid input! Please enter a valid number (1, 2, 3, 4, 5).")

        # Getting the new information  for updating
        new_detail = input("Enter the new detail: ")
        file_path = "products.txt"
        read_data_from_file(file_path)
        update_detail(lines, product_id, field_to_update, new_detail)

    elif ans == "3":  # Add a new supplier
        supplier_id = input("Enter Supplier ID: ")
        supplier_name = input("Enter Supplier Name: ")
        contact_details = input("Enter Supplier Contact: ")

        # Format and save supplier data
        new_format = supplier_format_order(supplier_id, supplier_name, contact_details)
        with open("suppliers.txt", "a") as f:
            f.write(f"{new_format}\n")  # create a new list

        print("Order placed successfully.")

    elif ans == "4":  # Place an order
        order_id = input("Order id: ") 
        product_id = input("Product id: ") 
        file_path = "products.txt"  # provide file path for checking
        lines = read_data_from_file(file_path)

        if check_product_valid(lines, product_id):
            print("Product ID is invalid and not included. Proceeding with the operation.")  # proceed with adding or updating the product
            continue
        else:
            pass  # Handle the case for duplicate product ID

        print("Product ID is valid. Proceeding with the operation.")

        current_stock = None
        for line in lines:
            product_data = eval(line.strip())
            if product_data[0] == product_id:
                current_stock = int(product_data[4])
                break

        if current_stock is None:
            print("Unable to retrieve stock details. Please try again.")
            continue

        while True:
            try:
                order_quantity = int(input("Quantity: "))
                if order_quantity < 0:
                    raise ValueError
                if order_quantity > current_stock:
                    print(f"Insufficient stock! Available stock is {current_stock}. Please reduce your order quantity.")
                else:
                    break
            except ValueError as e:
                print(f"Invalid input: {e}. Please enter a non-negative integer for quantity.")

        updated_stock = dynamic_stock_counter(lines, product_id, order_quantity)  # calculation for the product stock
        update_detail(lines, product_id, "5", updated_stock)  # update the new count stock

        order_date = input("Order date: ") 
        new_format = replace_format_order(order_id, product_id, order_quantity, order_date) 
            
        with open("orders.txt", "a") as f: 
            f.write(f"{new_format}\n")  # create a new list
        print("Order placed successfully.")

    elif ans == "5":
        print("Inventory".center(40))
        # Define the separator line based on the width of the table columns
        separator_line = "+" + "-" * 12 + "+" + "-" * 22 + "+" + "-" * 12 + "+"
        print(separator_line)
        # Print the table header
        print("|", "Product id".ljust(10), "|", "Name".center(20), "|", "Quantity".center(10), "|")
        # Print the separator line
        print(separator_line)

        # Read the data from file
        try:
            lines = read_data_from_file("products.txt")
            for line in lines :
             data = eval(line.strip())
             print("|", data[0].ljust(10), "|", data[1].center(20), "|", data[4].center(10), "|")
                    # Print the separator line
             print(separator_line)
             
        except TypeError:
            print("=" * 50)

        time.sleep(5)

    elif ans == "6":
        print("\n" + "General Report".center(76))
        print("="*76)

        separator_line_sales = "+" + "-" * 14 + "+" + "-" * 14 + "+" + "-" * 14 + "+" + "-" * 14 + "+" + "-" * 14 + "+"
        times = datetime.datetime.now().strftime("%Y-%m-%d")

        #  Sales Report
        print("|", "Sales Report".center(57), times.rjust(14), "|")
        print("=" * 76)
        print(separator_line_sales)
        print("|", "Order id".center(12), "|", "Product id".center(12), "|", "Price".center(12), "|", "Quantity".center(12), "|", "RM".rjust(12), "|")
        print(separator_line_sales)
        try:
            path_order = read_data_from_file("orders.txt")
            for line in path_order:
                data = eval(line.strip())
                path_product = read_data_from_file("products.txt")
                for line_price in path_product:
                    price = eval(line_price.strip())
                    if data[1] == price[0]:  # Corrected index to match
                        total = float(data[2]) * float(price[3])
                        total_str = f"{total:.1f}"
                        total_sales += total
                        print("|", data[0].center(12), "|", data[1].center(12), "|", str(price[3]).center(12), "|",
                              data[2].center(12), "|", total_str.rjust(12), "|")
                        print(separator_line_sales)
        except TypeError:
            print("=" * 76)

        print("|", "Net sales".center(57), "|", f"{total_sales:.1f}".rjust(12), "|")
        print(separator_line_sales)
        print("="*76)

        print("\n")

        #  Low Stock Report
        separator_line_stock = "+" + "-"*20 + "+" + "-"*36 + "+" + "-"*16 + "+"
        print("=" * 76)
        print("|", "Low Stock Report".center(57), times.rjust(14), "|")
        print("=" * 76)
        print(separator_line_stock)
        print("|", "Product id".center(18), "|", "Name".center(34), "|", "Quantity".center(14), "|")
        print(separator_line_stock)
        try:
            path_product = read_data_from_file("products.txt")
            for line in path_product:
             count_number += 1
             stock_count += sum([int(eval(line.strip())[4])])
            median_number = stock_count / count_number
            for line_stock in path_product:
                stock = eval(line_stock.strip())
                if int(stock[4]) <= median_number:
                    print("|", stock[0].center(18), "|", stock[1].center(34), "|", stock[4].center(14), "|")
                    print(separator_line_stock)
            print("=" * 76)
        except TypeError:
            print("=" * 76)

        print("\n")

        #  Supplier Orders
        separator_line_supplier = "+" + "-"*20 + "+" + "-"*32 + "+" + "-"*20 + "+"
        print("=" * 76)
        print("|", "Supplier Orders".center(72), "|")
        print("="*76)
        print(separator_line_supplier)
        print("|", "Supplier id".center(18), "|", "Name".center(30), "|", "Contact details".center(18), "|")
        print(separator_line_supplier)
        try:
            path_supplier = read_data_from_file("suppliers.txt")
            for line in path_supplier:
                data = eval(line.strip())
                print("|", data[0].center(18), "|", data[1].center(30), "|", data[2].center(18), "|")
                print(separator_line_supplier)
            print("=" * 76)
        except TypeError:
            print("=" * 76)

        time.sleep(5)
        print("\n")

    elif ans == "7":
        print("="*40)
        print("Appreciate for using".center(40))
        print("! Wish you all the best !".center(40))
        print("="*40)
        time.sleep(5)
        break

    else:
        print("Invalid! Please select a valid number.")