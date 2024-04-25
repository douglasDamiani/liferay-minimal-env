echo "02-run-dump-with-has-file.sh"

folder_path="/opt/oracle/admin/ORCLCDB/dpdump/"

cp /dump/* "$folder_path"

# get Files inside of path in $folder_path and get only the first with extension .dmp 
first_dmp_file=$(find "$folder_path" -type f -name "*.dmp" -print -quit)

if [ -n "$first_dmp_file" ]; then
    # get File name
    file_name=$(basename "$first_dmp_file")

    echo "file with .dmp found in: $first_dmp_file / name: $file_name"
    impdp SYSTEM/1234@localhost:1521/ORCLCDB DUMPFILE="$file_name" SCHEMAS=LPORTAL
else
    echo "No .dmp files found in the specified folder."
fi





