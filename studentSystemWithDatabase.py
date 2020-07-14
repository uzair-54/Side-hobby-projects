import pyodbc

conn = pyodbc.connect(
    "Driver={SQL Server Native Client 11.0};" #using the driver for connection
    "Server=DESKTOP-83LHPKS;"                 #defining the server name
    "Database=studentSystem;"                 #defining the database name
    "Trusted_Connection=yes;"                 #verifying the connection
)

cur = conn.cursor()

print("Please select a number to Insert, update or view your record") #main selection
print("1)Insert 2)View")

while True:
    mainSelection = int(input())                #self explinatory
    if mainSelection < 1 or mainSelection > 2:  #self explinatory
        print("Wrong input please try again")   #self explinatory
    else:
        break

def insert():
    
    id = input("Please enter you Id ")                                           #getting id
    fullName = input("Please enter you Full name ")                              #getting name

    cur.execute("insert into students values ({0},'{1}')".format(id,fullName))   #executing the query to insert the data into the table
    cur.commit()                                                                 #commiting the changes to the database

def read():
    inputSearch = input("Enter Id number to find your record ")                     #getting the id to search for the student

    cur.execute("select * from students where id like ({0})".format(inputSearch))   #executing the query to select the data from the table
    for row in cur:
        print(row)

if mainSelection == 1:  #operation on main selection
    insert()

else:
    read()