import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';
import * as moment from 'moment';

import { BookingsServiceService } from '../bookings-service.service';
import { WarehouseServiceService } from '../warehouse-service.service';



@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent implements OnInit {
partitionData:any[];
data:any;
name:any;
phone:any;
email:any;
ownerMail : any;
pid : any;
cost:any;
sqft:any;
partId : any
location:any;
wid: any;
startDate:any;
endDate:any;

calcMonth:any;
 
arrayDate1: any =[];
arrayDate2: any =[];

  constructor(private date: DatePipe, private myRoute: Router, private whService:WarehouseServiceService,private route:ActivatedRoute, private bookingService :BookingsServiceService) { }

  ngOnInit() {


    this.data = (JSON.parse(sessionStorage.getItem('details')));
    console.log(this.data);


    console.log(this.data.jti);
    console.log(this.data.sub);
    console.log(this.data.iss);
    console.log(this.data.aud);
    this.name = this.data.jti;
    this.phone = this.data.sub;
    this.email = this.data.iss;
  
  
    const myVal = this.route.snapshot.paramMap.get('id');
    this.pid = this.route.snapshot.paramMap.get('pid');
    this.wid = myVal;
    console.log("Justlike that..."+this.wid);
    
    this.whService.getWarehouseData(myVal).subscribe(data=>
      {
  this.wid = myVal;
       this.partitionData=data;
       console.log(this.partitionData);
        this.ownerMail = data.ownerMail;
       this.partId = parseInt(this.pid) - 1;
       console.log(this.partId+"PartIDddddddddddddddddd");
       console.log(data.partitions[this.partId].cost);
       console.log(data.partitions[this.partId].sqft);
       this.location = data.address.area;

       this.cost = (data.partitions[this.partId].cost);
      this.sqft = (data.partitions[this.partId].sqft);

      console.log("Justlike that..."+this.wid);


      
   
    });
  }


  BookNow(date1,date2) {
    
    console.log("Book Now works...", moment,new Date(date1), date2);
    let start = moment(date1);
    let end = moment(date2);
    let dif = end.diff(start, 'days');
    let months = Math.round(dif/30);
    this.calcMonth = months;

    console.log(months, dif,"this is Moment Formatted Date")
    let obj={
      bookingIdentity:{
        warehouseId:this.wid,
        pid: this.pid
      },
      userName : this.name,
      userMailId : this.email,
      userMobile : this.phone,
      ownerMail : this.ownerMail,
      sqft :this.sqft,
      location: this.location,
      startDate : this.startDate,
      endDate: this.endDate,
      cost:this.cost,
      totalCost: this.cost * this.calcMonth
    }

    console.log(obj);

    this.bookingService.postBooking(obj).subscribe();
    this.myRoute.navigateByUrl("/user-dashboard");  
  }
}