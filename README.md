# TextClassification #

- Code for text classification
	- Reuters
	- 20newsgroups
- Bulk of the work (experiments) done through ARKWater
	- Data deserialized by this project




# CorporateRelationExtraction #

This repository contains code for extracting typed corporate relationships 
from press release documents as part of the Sloan corporate network extraction
project.  This file gives an overview of the organization of the
code, how to run the various components, and some notes about possible 
future improvements.  You can get an idea of how things work by reading 
through the other Sloan documentation included in the Sloan project 
distribution (in the Sloan tarball), reading through
this document, and then reading through the description of each class
located at the top of each code file.  A lot of the documentation within
the code refers to the tech report, so it's important that you read over
that document before doing anything else.

The Sloan tarball which contains the rest of the documents related to the
Sloan project is included in this repository under the *files* directory.
If you've just received the link to this repository, then you should start
by extracting the files and documentation from that tarball.  It contains
pointers to several over repositories that are part of the overarching project.

In general, the library Jars included in the tarball should be up-to-date except
for *ark-water.jar* which contains the ARKWater project which is actively
being developed.  When you're setting up the code for the Sloan projects, you
may need to check out and compile the most recent version of ARKWater (from 
https://github.com/forkunited/ARKWater).  This should get rid of any errors
or warnings that come up.

## Layout of the project ##

The code is organized into the following packages in the *src* directory:

*	*corp.data* - Classes for loading corporation meta-data, gazetteers,
LDA output, and other resources into memory. 

*	*corp.data.annotation* - Classes for loading annotated press release
documents and Stanford pipeline output into memory.

*	*corp.data.feature* - Classes for computing feature values from annotated 
press release documents.

*	*corp.experiment* - Classes for running experiments that train and 
evaluate the relation extraction model, containing the main functions where the
experiments start running.  The experiments are deserialized from
the configuration files in the *experiments* directory.  

*	*corp.model* - Classes representing the relation extraction model. 

*	*corp.model.evaluation* - Classes used by *corp.experiment* to evaluate
the model.

*	*corp.scratch* - Code for performing miscellaneous tasks.  The files in
this directory contain the main functions where the code starts running.

*	*corp.test* - Code for unit testing.  Most of the code hasn't been  
systematically tested, so this is practically empty.

*	*corp.util* - Miscellaneous utility classes.

*corp.scratch* and *corp.experiment* contain the entry points for the code,
but *corp.scratch* just performs miscellaneous tasks.  So if you're trying
to understand how this library is used to train and test the extraction
models, you should start by looking at classes in *corp.experiment*.

The *experiments* directory contains experiment configuration files for 
running experiments using *corp.experiment* classes.  The 
*experiments/ablation* sub-directory contains "ablation studies" 
experiments, the "experiments/test" directory contains experiments 
for testing the final model on the test data set, and the remaining 
sub-directories contain experiments that were run to choose features
for the various logistic regressions at different labels in the 
taxonomy tree model (see the Sloan technical report for more information).

## How to run things ##

Before running anything, you need to configure the project for your local 
setup.  To configure, do the following:

1.  Copy files/build.xml and files/corp.properties to the top-level directory
of the project. 

2.  Fill out the copied corp.properties and build.xml files with the 
appropriate settings by replacing text surrounded by square brackets.
