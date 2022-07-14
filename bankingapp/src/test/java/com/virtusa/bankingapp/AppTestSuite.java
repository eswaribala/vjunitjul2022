package com.virtusa.bankingapp;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@SelectPackages({"com.virtusa.bankingapp.dao","com.virtusa.bankingapp.models"})

@IncludeTags("dev")
@Suite
public class AppTestSuite 
{
  
}
