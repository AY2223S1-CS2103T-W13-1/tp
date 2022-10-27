---
layout: page title: User Guide
---

StudMap (SM) is a desktop app for managing your students, optimized for use via a Command Line Interface (CLI) while
still having the benefits of a Graphical User Interface (GUI). If you can type fast, SM can get your student management
tasks done faster than traditional GUI apps.

* Table of Contents {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `studmap.jar` [Coming soon].

1. Copy the file to the folder you want to use as the _home folder_ for your StudMap.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
   <!-- TODO: ![Ui](images/Ui.png) -->
   [Sample UI To be added]

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all students.

    * **`add`**`n/John Doe m/CS2103T s/E1234567` : <br> Adds a student
      named `John Doe` to the StudMap.

    * **`delete`**`3` : Deletes the 3rd student shown in the current list.

    * **`clear`** : Deletes all students.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

<!-- TODO: ![help message](images/helpMessage.png) -->
[Sample UI To be added]

Format: `help`

### Adding a student: `add`

Adds a student to the StudMap.

Format: `add n/NAME m/MODULE s/ID [p/PHONE] [e/EMAIL] [g/GITNAME] [h/HANDLE] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: <b>Tip:</b>
A student can have any number of tags (including 0)
</div>

Examples:

* `add n/John Doe m/CS2103T s/E1234567`
* `add n/Betsy Crowe t/PotentialTA e/betsycrowe@example.com s/E3141592 m/CS2101 p/1234567`
* `add n/Silos Yao t/StrongStudent g/silosyao s/E1234567 m/MA5203`

### Listing all students : `list`

Shows a list of all students in the StudMap.

Format: `list`

### Editing a student : `edit`

Edits an existing student in the StudMap.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [m/MODULE] [s/ID] [g/GITUSER] [h/TELEHANDLE] [t/TAG]…​`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list.
  The index **must be a positive integer** 1, 2, 3, …​ or use `all` to edit all students currently displayed.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student's tags by typing `t/` without specifying any tags after it.
* You can remove the student's phone, email, GitName, TeleHandle by typing `p/`, `e/`, `g/`, `h/` respectively.

Examples:

* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st student to be `91234567`
  and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.

### Locating students by name: `find`

Finds students whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Students matching at least one keyword will be returned (i.e. `OR` search). e.g. `Hans Bo` will return `Hans Gruber`
  , `Bo Yang`

Examples:

* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  <!-- TODO: ![result for 'find alex david'](images/findAlexDavidResult.png) -->
  [Sample UI To be added]

### Deleting a student : `delete`

Deletes the specified student from the StudMap.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `delete 2` deletes the 2nd student in the StudMap.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the StudMap.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

StudMap data are saved in the hard disk automatically after any command that changes the data. There is no need to save
manually.

### Editing the data file

StudMap data are saved as a JSON file `[JAR file location]/data/studmap.json`. Advanced users are welcome to update data
directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: <b>Caution:</b>
If your changes to the data file makes its format invalid, StudMap will discard all data and start with an empty data file at the next run.
</div>

### Sorting the students: `sort`

[Coming soon]

### Filtering the students: `filter`

[Coming soon]

### Mark student as present: `mark`

[Coming soon]

### Unmark attendance of a student: `unmark`

[Coming soon]

### Add tag to a student: `addtag`

[Coming soon]


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous StudMap home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**
Add** | `add n/NAME m/MODULE s/ID [p/PHONE] [e/EMAIL] [g/GITNAME] [h/HANDLE] [t/TAG]…​` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com m/CS2103T s/E1234567 g/user1 h/@user1 t/friends t/owesMoney`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**
Edit** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [m/MODULE] [s/ID] [g/GITUSER] [h/TELEHANDLE] [t/TAG]…​` <br> e.g.,`edit 1 p/91234567 e/johndoe@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**Sort** | `sort`
**Filter** | `filter`
**Mark** | `mark`
**Unmark** | `unmark`
**Add tag** | `addtag`
