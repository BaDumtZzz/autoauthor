import cx_Oracle

USER = "sys"
PASSWORD = "123"
DSN = "localhost:1521/orcl"

con= cx_Oracle.connect(USER, PASSWORD, DSN, mode=cx_Oracle.SYSDBA, encoding="UTF-8")

def insert_dept(n_dept, s_dname):
    cur = con.cursor()
    cur.execute("insert into files (author, path) values (:1, :2)", (n_dept, s_dname))
    cur.close()
    con.commit()
    con.close()

def update_model(group):
    cur = con.cursor()
    cur.execute("update  model  set status = (:1) where group_id = (:2)", ("ready", group))
    cur.close()
    con.commit()
    con.close()

# # call the insert_dept function
# try:
#     insert_dept(1, "2 It's Python, Bitch")
# except Exception as e:
#     print(e)