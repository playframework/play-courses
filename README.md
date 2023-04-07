# play-courses

The CMT complete documentation is available [here](http://cmt.lunatech.com/docs/getting_started).

## CMT installation

- Download the [latest CMT release](https://github.com/lunatech-labs/course-management-tools/releases)
- Unzip the zip file downloaded
- Update your PATH to include the `course-management-tools/bin` folder in
  the folder in which you unzipped the downloaded zip file

The installation documentation about the CMT is available [here](http://cmt.lunatech.com/docs/install).

To check if the installation is a success try to run:

```
~ cmta
~ cmtc
```

If the possible parameters are returned the installation is successful.

## CMT course setup

- Clone the repository to a directory of your choice
- At the root of the repository run the studentify: `cmta studentify -f -m . -s [dir_path]`
- Set up the studentified course as the current course: `cmtc set-current-course -s [dir_path]`

Once this is done you can run `cmtc` to display the possible commands
