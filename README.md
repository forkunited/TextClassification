# TextClassification #

This repository contains code for testing training and evaluating models
on text classification data sets.  Specifically, the code is currently set
up to run on the following data sets:

* *Reuters-21578 R52* - From 
http://www.daviddlewis.com/resources/testcollections/reuters21578/ with the
R52 subset described at 
http://www.csmining.org/index.php/r52-and-r8-of-reuters-21578.html.  We 
use the R52 subset in current experiments because we only have models that
assign one label per document.

* *20 newsgroups* - From http://qwone.com/~jason/20Newsgroups/.

The bulk of the work for training and evaluating the models is done using the
ARKWater library (https://github.com/forkunited/ARKWater), and the code in
this project is mainly for deserializing and manipulating the data-sets and
loading them into the ARKWater objects.  

All of the code relies on having tokenized and JSON serialized versions of the
data sets.  You can generate the JSON serialized data sets by downloading the
original data from the URLs given above, and then running 
*textclass.scratch.ConstructTextClassDocuments20NewsGroups" or 
*textclass.scratch.ConstructTextClassDocumentsReuters21578" on them.

## Layout of the project ##

The code is organized into the following packages in the *src* directory:

*	*textclass.data* - Classes cleaning the text classification data and 
performing other miscellaneous tasks.

*	*textclass.data.annotation* - Classes for loading the text classification 
documents into memory.

*	*textclass.scratch* - Code for performing miscellaneous tasks.  The files in
this directory contain the main functions where the code starts running.

*	*textclass.util* - Miscellaneous utility classes.

*textclass.scratch* contains the entry points for the code. So if you're trying
to understand how this library uses ARKWater to train and test the extraction
models, you should start by looking at classes in *textclass.experiment*.

The *experiments* directory contains experiment configuration files for 
running experiments through the *Experiment* classes in *textclass.scratch* 
using ARKWater.

## How to run things ##

Before running anything, you need to configure the project for your local 
setup.  To configure, do the following:

1.  Copy *files/build.xml* and *files/textclass.properties* to the top-level directory
of the project. 

2.  Fill out the copied *textclass.properties* and *build.xml* files with the 
appropriate settings by replacing text surrounded by square brackets.
